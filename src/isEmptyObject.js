/**
 * 判断是否是一个空对象
 *
 * React Native App
 * http://1mm.win
 * @author sheng.yu
 */
'use strict';

const isEmptyObject = (e) => {
    if (null === e || '' === e || [] === e || {} === e || typeof e == 'undefined') {
        return true;
    }
    if (typeof e == 'object' && typeof e.alert == 'function') {
        return false;
    }
    try {
        if (Object.keys(e).length < 1) {
        return true;
        }
    } catch (error) {
    }
    let t;
    for (t in e)
        return !1;
    return !0
}
export default isEmptyObject;
