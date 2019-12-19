package com.zaixiaoqu.picker.component;

/**
 * Created by Eleken. on 13.12.16.
 */
import com.aigestudio.wheelpicker.WheelPicker;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.text.ReactFontManager;
import android.graphics.Color;
import android.graphics.Typeface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WheelPickerManager extends SimpleViewManager<WheelPicker>  implements WheelPicker.OnItemSelectedListener{

    public static final String REACT_CLASS = "WheelPicker";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected WheelPicker createViewInstance(ThemedReactContext context) {
        WheelPicker wheelPicker = new PatchedWheelPicker(context);
        wheelPicker.setOnItemSelectedListener(this);
        return wheelPicker;
    }

    @ReactProp(name = "data" )
    public void setData(WheelPicker wheelPicker, ReadableArray data) {
        if (wheelPicker!=null){
            List<String> emptyList = new ArrayList<>();
            if (null == data || data.size() < 1) {
                try {
                    wheelPicker.setData(emptyList);
                } catch (Exception e) {

                }
                return;
            }
            try {
                List<Integer> dataInt = new ArrayList<>();
                for (int i = 0; i <data.size() ; i++) {
                    dataInt.add(data.getInt(i));
                }
                wheelPicker.setData(dataInt);
            } catch (Exception e){
                try {
                    List<String> dataString = new ArrayList<>();
                    for (int i = 0; i <data.size() ; i++) {
                        dataString.add(data.getString(i));
                    }
                    wheelPicker.setData(dataString);
                } catch (Exception ex){
                    ex.printStackTrace();
                    wheelPicker.setData(emptyList);
                }
            }
            try {
                wheelPicker.setSelectedItemPosition(0);
            } catch (Exception e) {
            }
        }
    }

    @ReactProp(name = "isCurved")
    public void setCurved(WheelPicker wheelPicker, Boolean isCurved) {
        if (wheelPicker!=null){
            wheelPicker.setCurved(isCurved);
        }
    }

    @ReactProp(name = "isCyclic")
    public void setCyclic(WheelPicker wheelPicker, Boolean isCyclic) {
        if (wheelPicker!=null){
            wheelPicker.setCyclic(isCyclic);
        }
    }

    @ReactProp(name = "isAtmospheric")
    public void setAtmospheric(WheelPicker wheelPicker, Boolean isAtmospheric) {
        if (wheelPicker!=null){
            wheelPicker.setAtmospheric(isAtmospheric);
        }
    }

    @ReactProp(name = "isSameWidth")
    public void setSameWidth(WheelPicker wheelPicker, Boolean isSameWidth) {
        if (wheelPicker!=null){
            wheelPicker.setSameWidth(isSameWidth);
        }
    }

    @ReactProp(name = "visibleItemCount")
    public void setVisibleItemCount(WheelPicker wheelPicker, int visibleItemCount) {
        if (wheelPicker!=null){
            wheelPicker.setVisibleItemCount(visibleItemCount);
        }
    }

    @ReactProp(name = "renderIndicator")
    public void setIndicator(WheelPicker wheelPicker, Boolean renderIndicator) {
        if (wheelPicker!=null){
            wheelPicker.setIndicator(renderIndicator);
        }
    }

    @ReactProp(name = "isCurtain")
    public void setCurtain(WheelPicker wheelPicker, Boolean isCurtain) {
        if (wheelPicker!=null){
            wheelPicker.setCurtain(isCurtain);
        }
    }

    @ReactProp(name = "itemTextAlign")
    public void setItemTextAlign(WheelPicker wheelPicker, String itemTextAlign) {
      if (wheelPicker!=null){
        List<String> alignList = Arrays.asList("center", "left", "right");
        int index = alignList.indexOf(itemTextAlign);
        wheelPicker.setItemAlign(index);
      }
    }

    @ReactProp(name = "selectedItemPosition")
    public void setSelectedItemPosition(WheelPicker wheelPicker, int selectedItemPosition) {
        if (wheelPicker!=null){
            try {
                wheelPicker.setSelectedItemPosition(selectedItemPosition, true);
                wheelPicker.setSelectedItemPosition(selectedItemPosition, true);
            } catch (Exception e) {
            }
        }
    }



    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        WritableMap event = Arguments.createMap();
        try {
            event.putString("data", (String) data);
        } catch (Exception e){
            e.printStackTrace();
            try {
                event.putInt("data", (Integer) data);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
        event.putInt("position", position);
        ((ReactContext) picker.getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(
            picker.getId(),
            "topChange",
            event);
    }




    // ==================================================
    // 开始
    // 大小设置
    // ==================================================

    @ReactProp(name = "indicatorSize")
    public void setIndicatorSize(WheelPicker wheelPicker, int indicatorSize) {
        if (wheelPicker != null){
            wheelPicker.setIndicatorSize(
                    (int) PixelUtil.toPixelFromDIP(indicatorSize)
            );
        }
    }

    @ReactProp(name = "itemSpace")
    public void setItemSpace(WheelPicker wheelPicker, int itemSpace) {
        if (wheelPicker!=null){
            wheelPicker.setItemSpace(
                    (int) PixelUtil.toPixelFromDIP(itemSpace)
            );
        }
    }

    /**
     * edit by yusheng at 2019-07-10 14:33
     *
     * @param wheelPicker
     * @param itemTextSize
     */
    @ReactProp(name = "itemTextSize")
    public void setItemTextSize(WheelPicker wheelPicker, int itemTextSize) {
        if (wheelPicker!=null){
            wheelPicker.setItemTextSize(
                    (int) PixelUtil.toPixelFromDIP(itemTextSize)
            );
        }
    }

    // ==================================================
    // 结束
    // 大小设置
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



    // ==================================================
    // 开始
    // 颜色设置
    // ==================================================

    @ReactProp(name = "backgroundColor", customType = "Color")
    public void setBackgroundColor(WheelPicker wheelPicker, Integer backgroundColor) {
        if (wheelPicker!=null){
            wheelPicker.setBackgroundColor(backgroundColor);
        }
    }

    @ReactProp(name = "curtainColor", customType = "Color")
    public void setCurtainColor(WheelPicker wheelPicker, Integer curtainColor) {
        if (wheelPicker!=null){
            wheelPicker.setCurtainColor(curtainColor);
        }
    }

    @ReactProp(name = "itemTextColor", customType = "Color")
    public void setItemTextColor(WheelPicker wheelPicker, Integer itemTextColor) {
        if (wheelPicker!=null){
            wheelPicker.setItemTextColor(itemTextColor);
        }
    }

    @ReactProp(name = "selectedItemTextColor", customType = "Color")
    public void setSelectedItemTextColor(WheelPicker wheelPicker, Integer selectedItemTextColor) {
        if (wheelPicker!=null){
            try {
                wheelPicker.setSelectedItemTextColor(selectedItemTextColor);
            } catch (Exception e) {

            }
        }
    }

    @ReactProp(name = "indicatorColor", customType = "Color")
    public void setIndicatorColor(WheelPicker wheelPicker, Integer indicatorColor) {
        if (wheelPicker!=null){
            wheelPicker.setIndicatorColor(indicatorColor);
        }
    }

    // ==================================================
    // 结束
    // 颜色设置
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
