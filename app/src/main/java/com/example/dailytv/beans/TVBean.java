package com.example.dailytv.beans;

import java.util.List;

/**
 * Created by xiaomao on 2016/7/7.
 */
public class TVBean{


    /**
     * title : 央视
     * programs : [{"name":"CCTV1","url":"http://ivi.bupt.edu.cn/hls/cctv1.m3u8"},{"name":"CCTV2","url":"http://ivi.bupt.edu.cn/hls/cctv2.m3u8"},{"name":"CCTV3","url":"http://ivi.bupt.edu.cn/hls/cctv3.m3u8"},{"name":"CCTV4","url":"http://ivi.bupt.edu.cn/hls/cctv4.m3u8 "},{"name":"CCTV5","url":"http://ivi.bupt.edu.cn/hls/cctv5.m3u8"},{"name":"CCTV6","url":"http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8"},{"name":"CCTV7","url":"http://ivi.bupt.edu.cn/hls/cctv7.m3u8"},{"name":"CCTV8","url":"http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8"},{"name":"CCTV9","url":"http://ivi.bupt.edu.cn/hls/cctv9.m3u8"},{"name":"CCTV10","url":"http://ivi.bupt.edu.cn/hls/cctv10.m3u8"},{"name":"CCTV11","url":"http://ivi.bupt.edu.cn/hls/cctv11.m3u8"},{"name":"CCTV12","url":"http://ivi.bupt.edu.cn/hls/cctv12.m3u8"},{"name":"CCTV13","url":"http://ivi.bupt.edu.cn/hls/cctv13.m3u8"},{"name":"CCTV14","url":"http://ivi.bupt.edu.cn/hls/cctv14.m3u8"},{"name":"CCTV15","url":"http://ivi.bupt.edu.cn/hls/cctv15.m3u8"},{"name":"CHC电影","url":"http://ivi.bupt.edu.cn/hls/chchd.m3u8"}]
     */

    private String title;
    /**
     * name : CCTV1
     * url : http://ivi.bupt.edu.cn/hls/cctv1.m3u8
     */

    private List<ProgramsBean> programs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProgramsBean> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramsBean> programs) {
        this.programs = programs;
    }

    public static class ProgramsBean {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
