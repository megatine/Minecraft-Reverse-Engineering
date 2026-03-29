# Minecraft-Reverse-Engineering

Bu depo, Minecraft sunucu-istemci (client-server) iletişimini analiz etmek ve ağ paketlerini manipüle ederek çeşitli otomasyon sistemleri geliştirmek amacıyla oluşturulmuş açık kaynaklı bir eğitim serisidir.

## 📂 Bölümler
* **[Bölüm 1: Auto Fish (Paket Analizi ve Otomasyon)](./Bolum1-AutoFish)** 📺 **[YouTube'da İzle](https://www.youtube.com/watch?v=P_RFgd3CDeI)**
* **[Bölüm 2: Boat Fly (Kaynak Kod Analizi ve Sunucu Bypass)](./Bolum2-Fly)** 📺 **[YouTube'da İzle](https://www.youtube.com/watch?v=V1jpkKWJkzQ)**
* **[Bölüm 3: ESP (Dinamik vs Statik Kod Analizi)](./Bolum3-ESP)** 📺 **[YouTube'da İzle](https://www.youtube.com/watch?v=u8E9Nm1zN3c)**
* **[Bölüm 4: OP Exploit (Sunucu Paneli Zafiyeti)](./Bolum4-OP)** 📺 **[YouTube'da İzle](https://www.youtube.com/watch?v=PtnQrsV4sz8)**
* **[Bölüm 5: Reach Hack (Ağ Paketi Manipülasyonu ve TP-Aura)](./Bolum5-Reach)** 📺 **[YouTube'da İzle](https://www.youtube.com/watch?v=cJI1h0lCksA)**
  
## 🛠️ Kurulum ve Derleme
Bu repodaki projeler Fabric API üzerine inşa edilmiştir. Kodu kendi ortamınızda derlemek için aşağıdaki adımları izleyin:

1. Terminali açın ve ilgili bölümün klasörüne gidin:
  cd <bölüm-klasörü-adı>
2. Gradle Wrapper kullanarak projeyi derleyin:
  ./gradlew build
3. Başarılı bir derleme sonrası, kullanıma hazır `.jar` dosyası `build/libs` dizininde oluşturulacaktır.

## 📦 Hazır Sürümler
Kaynak kodu derlemekle vakit kaybetmek istemeyen kullanıcılar, test edilmiş ve derlenmiş hazır `.jar` dosyalarını deponun sağ tarafında bulunan **Releases** sekmesinden indirebilirler.

## ⚠️ Yasal Uyarı (Disclaimer)
Bu proje tamamen sistem mimarisini anlamak, tersine mühendislik pratikleri yapmak ve siber güvenlik araştırmaları yürütmek, eğitim amacıyla tasarlanmıştır. Bu kodların halka açık, çok oyunculu sunucularda kullanılması haksız rekabet yaratabilir ve hesabınızın kalıcı olarak yasaklanmasına yol açabilir. Doğabilecek tüm hukuki ve sistemsel sorumluluk kodu çalıştıran son kullanıcıya aittir.
