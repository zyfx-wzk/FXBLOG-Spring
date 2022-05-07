package com.example.fxblog.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fxblog.other.Exception.ReturnException;
import com.example.fxblog.service.MetaService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @Author 王志康
 * @Date 2022/5/6 21:08
 */
@Slf4j
@Component
@EnableScheduling
public class MusicUtil {
    public static final String MUSIC_LIST = "Music_List";
    public static final String MUSIC_UUID = "music_uuid";
    private final MetaService metaService;
    private final RedisUtil redisUtil;

    MusicUtil(RedisUtil redisUtil, MetaService metaService) {
        this.redisUtil = redisUtil;
        this.metaService = metaService;
        loadingMusicList();
    }

    private String e() {
        return RandomUtil.randomString(4);
    }

    /**
     * 伪造密钥
     */
    private String fakeMid() {
        return SecureUtil.md5(e() + e() + "-" + e() + "-" + e() + "-" + e() + "-" + e() + e() + e());
    }

    public void delMusicList() {
        redisUtil.remove(MUSIC_LIST);
    }

    public Object getMusicList() {
        return redisUtil.get(MUSIC_LIST);
    }

    @Scheduled(cron = "0 0 6 * * ?")
    public void loadingMusicList() {
        delMusicList();
        String uuid = metaService.getMetaStringValue(MUSIC_UUID);
        JSONArray array = (JSONArray) JSONUtil
                .parseObj(HttpUtil.get("https://m.kugou.com/plist/list/" + uuid + "?json=true"))
                .getByPath("list.list.info");
        List<JSONObject> list = array.toList(JSONObject.class);
        List<JSONObject> result = new ArrayList<>();
        for (JSONObject object : list) {
            try {
                JSONObject res = JSONUtil.parseObj(HttpUtil.get("https://wwwapi.kugou.com//yy/index.php?r=play/getdata"
                        + "&hash=" + object.getStr("hash") + "&mid=" + fakeMid()
                        + "&platid=4" + "&album_id=" + object.getStr("album_id")));
                String url = (String) res.getByPath("data.play_url");
                if (url.length() != 0) {
                    result.add(new JSONObject()
                            .putOnce("url", url).putOnce("cover", res.getByPath("data.img"))
                            .putOnce("lrc", res.getByPath("data.lyrics"))
                            .putOnce("name", res.getByPath("data.song_name"))
                            .putOnce("artist", res.getByPath("data.author_name")));
                }
            } catch (Exception e) {
                JSONObject res = JSONUtil.parseObj(HttpUtil.get("https://m.kugou.com/app/i/getSongInfo.php" +
                        "?cmd=playInfo&hash=" + object.getStr("hash")));
                String url = res.getStr("url");
                String cover = res.getStr("album_img").replaceAll("\\{size}", "100");
                if (url.length() != 0) {
                    result.add(new JSONObject()
                            .putOnce("url", url).putOnce("cover", cover).putOnce("lrc", "")
                            .putOnce("name", res.getByPath("singerName"))
                            .putOnce("artist", res.getByPath("songName")));
                }
            }
        }
        log.info("音乐列表加载完成,当前Redis缓存中共" + result.size() + "份歌曲信息");
        redisUtil.add(MUSIC_LIST, result, 0);
    }
}
