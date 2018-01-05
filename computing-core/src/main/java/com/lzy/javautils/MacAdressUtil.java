package com.lzy.javautils;

import com.lzy.dao.impl.VendorMacDaoImpl;
import com.lzy.bean.VendorMacBean;
import org.apache.log4j.Logger;

/**
 * Mac 地址解析类
 * Created by Liu Zi Yang on 2017/6/24 18:22.
 * E-mail address is kobeliuziyang@qq.com
 * Copyright © 2017 Liuziyang. All Rights Reserved.
 *
 * @author Liuziyang
 */
public class MacAdressUtil {
    // 得到log记录器
    private static final Logger logger = Logger.getLogger(MacAdressUtil.class);

    /**
     * 根据Mac地址获取制造厂商
     *
     * @param macAddress
     * @return
     */
    public static String getVendorByMac(String macAddress) {

        return getVendorByMacPrefix(getMacPrefix(macAddress));
    }


    /**
     * 根据制造商获取品牌
     *
     * @param macAddress
     * @return
     */
    public static String getBrandByMac(String macAddress) {
        String vendor = getVendorByMac(macAddress);
        String brand = "Unknown";
        try {
            brand = vendor.split(" ")[0].trim();
        } catch (Exception e) {
            if (null != vendor)
                brand = vendor;
        }
        return brand;
    }


    /**
     * 获取Mac地址前缀
     *
     * @param macAddress
     * @return
     */
    private static String getMacPrefix(String macAddress) {
        String macPrefix;
        try {
            macPrefix = macAddress.substring(0, 8).toUpperCase();
        } catch (Exception e) {
            macPrefix = null;
//            logger.error(e.getStackTrace());
//            System.err.println(e.getStackTrace());
        }
        return macPrefix;
    }


    /**
     * 根据Mac地址前缀获取制造商
     *
     * @param macPrefix
     * @return
     */
    private static String getVendorByMacPrefix(String macPrefix) {
        try {
            VendorMacDaoImpl vendorMacDaoImpl = new VendorMacDaoImpl();
            VendorMacBean vendorMacBean = vendorMacDaoImpl.getVendorByMac(macPrefix);
            return vendorMacBean.getVendorName();
        } catch (Exception e) {
//            logger.error(e.getStackTrace());
//            System.err.println(e.getStackTrace());
        }
        return null;
    }


}
