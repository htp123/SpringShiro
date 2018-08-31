package com.htp.shiro;

        import org.apache.shiro.authc.AuthenticationInfo;
        import org.apache.shiro.authc.AuthenticationToken;
        import org.apache.shiro.authc.pam.AuthenticationStrategy;
/*AllSuccessfulStrategy;
AtLeastOneSuccessfulStrategy
FirstSuccessfulStrategy
 */
        import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
        import org.apache.shiro.realm.Realm;

        import java.util.Collection;

/**
 * Created by Administrator on 2018/8/3.
 */

public class UserMultiRealmAuthenticator extends ModularRealmAuthenticator {
    //此处其实和源码基本一样
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        AuthenticationStrategy strategy = getAuthenticationStrategy();

        AuthenticationInfo authInfo = strategy.beforeAllAttempts(realms,token);

        for (Realm realm:realms){

            System.out.println(">>>>>>>>>>>>>>>>><<<<<<<<<<<<");
            AuthenticationInfo info = null;
            authInfo = strategy.beforeAttempt(realm,token,authInfo);
            if (realm.supports(token)){
                Throwable t = null;
                try {
                    info = realm.getAuthenticationInfo(token);
                }catch (Exception e){
                    System.out.println(".......");
                }

                authInfo = strategy.afterAttempt(realm,token,info,authInfo,t);
            }

            //只需要单个成功的情况
            //为何此处还需要做判断，strategy已经封装了
           /* if (authInfo != null && !authInfo.getPrincipals().isEmpty()){
                return authInfo;
            }
            else{

            }*/
        }
        authInfo = strategy.afterAllAttempts(token,authInfo);
        return authInfo;

//        return super.doMultiRealmAuthentication(realms, token);
    }
}
