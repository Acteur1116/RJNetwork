package com.renard.rjnetworkdemo.engine;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.renard.rjnetwork.local.table.DanmakuInfo;

import java.io.IOException;

/**
 * Created by Riven_rabbit on 12/28/20
 *
 * @author suyanan
 */
public class DanmakuInfoTypeAdapter extends TypeAdapter<DanmakuInfo> {

    private static final String TYPE = "type";
    private static final String CONTENT = "content";
    private static final String TIME = "time";
    private static final String TEXT_SIZE = "textSize";
    private static final String TEXT_COLOR = "textColor";
    private static final String USER_NAME = "userName";
    private static final String VID = "vid";

    @Override
    public void write(JsonWriter out, DanmakuInfo value) throws IOException {
        out.beginObject();
        out.name(TYPE).value(value.getType());
        out.name(CONTENT).value(value.getContent());
        out.name(TIME).value(value.getTime());
        out.name(TEXT_SIZE).value(value.getTextSize());
        out.name(TEXT_COLOR).value(value.getTextColor());
        out.name(USER_NAME).value(value.getUserName());
        out.name(VID).value(value.getVid());
        out.endObject();
    }

    @Override
    public DanmakuInfo read(JsonReader in) throws IOException {
        DanmakuInfo danmakuInfo = new DanmakuInfo();
        in.beginObject();
        while (in.hasNext()) {
            String field = in.nextName();
            if (TYPE.equals(field)) {
                danmakuInfo.setType(in.nextInt());
            } else if (CONTENT.equals(field)) {
                danmakuInfo.setContent(in.nextString());
            } else if (TIME.equals(field)) {
                danmakuInfo.setTime(in.nextLong());
            } else if (TEXT_SIZE.equals(field)) {
                danmakuInfo.setTextSize((float) in.nextDouble());
            } else if (TEXT_COLOR.equals(field)) {
                danmakuInfo.setTextColor(in.nextInt());
            } else if (USER_NAME.equals(field)) {
                danmakuInfo.setUserName(in.nextString());
            } else if (VID.equals(field)) {
                danmakuInfo.setVid(in.nextString());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return danmakuInfo;
    }
}
