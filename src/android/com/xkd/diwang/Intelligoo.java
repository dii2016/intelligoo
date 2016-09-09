package com.xkd.diwang.intelligoo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import com.xkd.diwang.intelligoo.DeviceBean;
import com.intelligoo.sdk.LibDevModel;

import com.intelligoo.sdk.LibInterface.ManagerCallback;

public class Intelligoo extends CordovaPlugin  {
	
	@Override
	public boolean execute(String action, JSONArray args,
		final CallbackContext callbackContext) throws JSONException {
			if ("openDoor".equals(action)) {
				JSONObject device = args.getJSONObject(0);
				DeviceBean deviceBean = new DeviceBean();
				deviceBean.seteKey(device.getString("eKey"));
				deviceBean.setDevMac(device.getString("devMac"));
				deviceBean.setDevSn(device.getString("devSn"));
				deviceBean.setDevType(device.getInt("devType"));
				LibDevModel deviceModel = getLibDev(deviceBean);
				final JSONObject resultObj = new JSONObject();
				final ManagerCallback callback = new ManagerCallback() 
				{
					@Override
					public void setResult(final int result, Bundle bundle) 
					{
						try {
							resultObj.put("ret", result);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (result == 0x00) {
							callbackContext.success(resultObj);
						} else {
							callbackContext.error(resultObj);
						}
					}
				};
				int ret = LibDevModel.controlDevice(cordova.getActivity(), 0x00, deviceModel, null, callback);
				
				return true;
			}
			return false;
		}

	private LibDevModel getLibDev(DeviceBean dev) {
		LibDevModel device = new LibDevModel();
		device.devSn = dev.getDevSn();
		device.devMac = dev.getDevMac();
		device.devType = dev.getDevType();
		device.eKey = dev.geteKey();
		device.encrytion = dev.getEncrytion();
		device.endDate = dev.getEndDate();
		device.openType = dev.getOpenType();
		device.privilege = dev.getPrivilege();
		device.startDate = dev.getStartDate();
		device.useCount = dev.getUseCount();
		device.verified = dev.getVerified();
		return device;
	}
}
