package com.lyw.hystrixdemo.controller;

import com.lyw.hystrixdemo_api.Config;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>注释</p>
 *
 * @author liaoyiwei
 */
public abstract class BaseDemoController {

    @PostMapping("/config")
    public @ResponseBody
    String setConfig(@RequestParam("percent") int percent, @RequestParam("sleepTime") long sleepTime) {
        Config config = getConfig();
        config.setPercent(percent);
        config.setSleepTime(sleepTime);
        return String.format("percent %s  sleep time %s has been set", percent, sleepTime);
    }

    protected abstract Config getConfig();

}
