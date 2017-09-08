package org.styleru.hseday2017_2;


import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;
import com.yandex.metrica.YandexMetrica;

public class Application extends android.app.Application {
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                // VKAccessToken is invalid
                Toast.makeText(getApplicationContext(), "TOKEN_ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        vkAccessTokenTracker.startTracking();
        VKSdk.initialize(this);
        // Инициализация AppMetrica SDK
        YandexMetrica.activate(getApplicationContext(), "f1c7d778-0f0a-49bb-95a4-5713d7ad3d5e");
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this);

    }
}