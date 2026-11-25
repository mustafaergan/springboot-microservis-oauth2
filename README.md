# Spring Boot Mikroservis ve OAuth2 Örneği

Spring Boot 2.0 ile kurulmuş örnek bir mikroservis yapısı: OAuth2 yetkilendirme sunucusu, ortak yapılandırma modülü, bu modülü kullanan bir yönetim servisi ve Spring Boot Admin izleme sunucusu.

> **Not:** Bu depo 2018 yılında eğitim/örnek amaçlı yazıldı ve artık güncellenmiyor. Bağımlılıkları eskidir ve bilinen güvenlik açıkları içerir (Spring Security OAuth2 artık desteklenmiyor); üretimde kullanmayın.

## İçerik

| Klasör | Ne yapar | Port |
|---|---|---|
| `oauth2server/` | `@EnableAuthorizationServer` ile token üreten OAuth2 sunucusu (`/authservice/oauth/token`, kullanıcı bilgisi `/authservice/user`). İstemci ve kullanıcı tanımları `application.properties` ve `SecurityConfig` içinde | 9999 |
| `common/` | Diğer servislerin bağımlılık olarak kullandığı ortak modül: güvenlik, Swagger, Hazelcast önbellek, log4j2, ortam profilleri (`local`, `dev`, `stage`, `prod`) | – |
| `admin/` | `common` üzerine kurulu servis: Oracle'daki parametre ve çoklu dil mesaj tabloları için REST uçları (`/parameter`, `/message`), Couchbase'e log yazma (`/log`), mesajları veritabanından okuyan `MessageSource` | 8082 |
| `springserver/` | Servisleri izlemek için Spring Boot Admin sunucusu | 8081 |

`oauth2server/src/main/resources/img/` altındaki ekran görüntüleri token alma ve token ile istek atma adımlarını gösterir.

## Teknolojiler

- Java 8, Maven
- Spring Boot 2.0.3 / 2.0.5, Spring Cloud Finchley (OAuth2, Security)
- Spring Data JPA (Oracle, `ojdbc6` 12.1.0.2), Spring Data Couchbase 3.0
- Hazelcast, Springfox Swagger 2.9.2, log4j2, Lombok
- Spring Boot Admin 2.0.1

## Çalıştırma

1. `common` modülünü yerel Maven deposuna kurun: `cd common && mvn install`.
   `ojdbc6` Maven Central'da bulunmaz; Oracle JDBC sürücüsünü kendiniz indirip `mvn install:install-file` ile yerel depoya eklemeniz gerekir.
2. `common/src/main/resources/application-local.properties` dosyasındaki boş bırakılmış Oracle ve Couchbase bağlantı bilgilerini kendi ortamınıza göre doldurun.
3. Servisleri ayrı terminallerde başlatın:

```
cd springserver && mvn spring-boot:run
cd oauth2server && mvn spring-boot:run
cd admin && mvn spring-boot:run
```

4. Token almak için `oauth2server` ayarlarındaki istemci kimliğiyle (Basic Auth) `http://localhost:9999/authservice/oauth/token` adresine `grant_type=password` isteği gönderin; dönen `access_token` değerini `Authorization: Bearer ...` başlığıyla `http://localhost:9999/authservice/user` ucunda deneyebilirsiniz. `common` modülünde `@EnableResourceServer` yorum satırında olduğu için `admin` servisi şu haliyle token istemez. Depodaki istemci ve kullanıcı bilgileri yalnızca yerel deneme içindir, kendi değerlerinizle değiştirin.
