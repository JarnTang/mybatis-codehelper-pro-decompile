package com.ccnode.codegenerator.validate;

import com.ccnode.codegenerator.myconfigurable.MyBatisCodeHelperApplicationComponent;
import com.ccnode.codegenerator.myconfigurable.Profile;
import com.ccnode.codegenerator.util.IpUtil;
import com.ccnode.codegenerator.util.ProjectHelperUtils;
import com.ccnode.codegenerator.validate.handler.ProjectRequest;
import com.ccnode.codegenerator.validate.request.CheckValidRequest;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.BuildNumber;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.net.HttpConfigurable;
import org.jetbrains.annotations.Nullable;

public class PPValidator
{
  public static boolean validate(Project project)
  {
    CheckValidRequest request = new CheckValidRequest();
    request.setUserPluginName("mybatisCodeHelperPro");
    request.setUserMac(IpUtil.getMacAddress());
    Boolean valid = Boolean.valueOf(MyValidator.isValid(request));
    UserProxy proxy = getUserProxyFromSetting();
    if (!valid.booleanValue()) {
      while (!valid.booleanValue())
      {
        String userKey = Messages.showInputDialog("the plugin is invalid  you can go to http://brucegesss.com/pay/view to register", "the plugin is not available", null);
        if (userKey == null) {
          return false;
        }
        ProjectRequest request1 = new ProjectRequest();
        request1.setKey(userKey);
        request1.setUserMac(IpUtil.getMacAddress());
        request1.setUserOs(System.getProperty("os.name"));
        request1.setUserPluginVersion(ApplicationInfo.getInstance().getBuild().asString());
        request1.setUserOsVersion("");
        request1.setProxy(proxy);
        request1.setUserPluginName("mybatisCodeHelperPro");
        MyValidator.validateTheProject(request1);
        valid = request1.getValid();
        if (!valid.booleanValue())
        {
          Messages.showErrorDialog(project, "the error msg is:" + request1.getErrorMsg(), "validate fail");
          int continueActivate = Messages.showOkCancelDialog(project, "继续激活，如果是网络原因，可考虑设置vpn或者离线激活\n 如果可以ping通brucege.com,请设置Intellij的proxy模式为Auto-Detect Proxy Setting\n可以联系微信gejun12311", "是否继续激活", null);
          if (continueActivate == 2) {
            break;
          }
        }
        else
        {
          MyBatisCodeHelperApplicationComponent.getProfile().setValid(true);
          ProjectHelperUtils.reopenAllProjectFiles();
        }
      }
    }
    MyBatisCodeHelperApplicationComponent.getProfile().setValid(valid.booleanValue());
    return valid.booleanValue();
  }

  public static boolean validateWihtOutProject()
  {
    CheckValidRequest request = new CheckValidRequest();
    request.setUserPluginName("mybatisCodeHelperPro");
    request.setUserMac(IpUtil.getMacAddress());
    Boolean valid = Boolean.valueOf(MyValidator.isValid(request));
    UserProxy proxy = getUserProxyFromSetting();
    if (!valid.booleanValue()) {
      while (!valid.booleanValue())
      {
        String userKey = Messages.showInputDialog("the plugin is invalid  you can go to http://brucege.com/pay/view to register", "the plugin is not available", null);
        if (userKey == null) {
          return false;
        }
        ProjectRequest request1 = new ProjectRequest();
        request1.setKey(userKey);
        request1.setUserMac(IpUtil.getMacAddress());
        request1.setUserOs(System.getProperty("os.name"));
        request1.setUserPluginVersion(ApplicationInfo.getInstance().getBuild().asString());
        request1.setUserOsVersion("");
        request1.setProxy(proxy);
        request1.setUserPluginName("mybatisCodeHelperPro");
        MyValidator.validateTheProject(request1);
        valid = request1.getValid();
        if (!valid.booleanValue())
        {
          Messages.showErrorDialog("the error msg is:" + request1.getErrorMsg(), "validate fail");
          int continueActivate = Messages.showOkCancelDialog("继续激活，如果是网络原因，可考虑设置vpn或者离线激活\n 如果可以ping通brucege.com,请设置Intellij的proxy模式为Auto-Detect Proxy Setting\n可以联系微信gejun12311", "是否继续激活", null);
          if (continueActivate == 2) {
            break;
          }
        }
        else
        {
          MyBatisCodeHelperApplicationComponent.getProfile().setValid(true);
          ProjectHelperUtils.reopenAllProjectFiles();
        }
      }
    }
    return valid.booleanValue();
  }

  @Nullable
  public static UserProxy getUserProxyFromSetting()
  {
    UserProxy proxy = null;
    HttpConfigurable proxySettings = HttpConfigurable.getInstance();
    if (proxySettings != null) {
      if ((proxySettings.USE_HTTP_PROXY) && (!StringUtil.isEmptyOrSpaces(proxySettings.PROXY_HOST)))
      {
        proxy = new UserProxy();
        proxy.setProxyHost(proxySettings.PROXY_HOST);
        proxy.setProxyPort(String.valueOf(proxySettings.PROXY_PORT));
        proxy.setProxyUserName(proxySettings.getProxyLogin());
        proxy.setProxyPassword(proxySettings.getPlainProxyPassword());
        proxy.setProxyMethod("http");
      }
      else if ((proxySettings.PROXY_TYPE_IS_SOCKS) && (!StringUtil.isEmptyOrSpaces(proxySettings.PROXY_HOST)))
      {
        proxy = new UserProxy();
        proxy.setProxyHost(proxySettings.PROXY_HOST);
        proxy.setProxyPort(String.valueOf(proxySettings.PROXY_PORT));
        proxy.setProxyUserName(proxySettings.getProxyLogin());
        proxy.setProxyPassword(proxySettings.getPlainProxyPassword());
        proxy.setProxyMethod("socks");
      }
    }
    return proxy;
  }

  public static ValidateResponse isValid()
  {
    CheckValidRequest request = new CheckValidRequest();
    request.setUserPluginName("mybatisCodeHelperPro");
    request.setUserMac(IpUtil.getMacAddress());
    boolean valid = MyValidator.isValid(request);
    MyBatisCodeHelperApplicationComponent.getProfile().setValid(valid);
    ValidateResponse response = new ValidateResponse();
    response.setIsValid(Boolean.valueOf(valid));
    response.setValidExpireDate(request.getReturnExpireDate());
    return response;
  }
}
