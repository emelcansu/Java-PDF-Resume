package org.example;

// Gerekli iText kütüphaneleri import ediliyor
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.properties.TextAlignment;

import java.awt.Desktop;  // PDF dosyasını otomatik açmak için kullanılır
import java.io.File;      // Dosya işlemleri için kullanılır
import java.io.FileNotFoundException;  // Dosya bulunamadığında atılacak hata
import java.io.IOException;  // IO işlemlerinde karşılaşılabilecek hatalar

public class CVGenerator {  // Sınıf adı


    public static void main(String[] args) {
        // PDF dosyasının kaydedileceği dosya yolu
        String pdfPath = "CV.odev.pdf";

        // Eklenecek resmin dosya yolu
        String imagePath = "src/main/resources/images/img.png";

        try {
            // PDF yazıcısı oluşturulur
            PdfWriter writer = new PdfWriter(pdfPath);

            // PDF dokümanı oluşturulur
            PdfDocument pdfDocument = new PdfDocument(writer);

            // Doküman üzerinde düzenleme yapmak için Document sınıfı oluşturulur
            Document document = new Document(pdfDocument);

            // Resmi sayfanın sol üst köşesine yerleştiriyoruz
            Image img = new Image(ImageDataFactory.create(imagePath));
            img.setFixedPosition(36, 700);  // Resmin sayfa üzerindeki konumu (x:36, y:700)
            img.scaleToFit(100, 100);  // Resmin boyutu ayarlanıyor, maksimum genişlik ve yükseklik 100 piksel olacak
            document.add(img);  // Resim PDF'e eklenir

            // Kişisel Bilgiler Yazdırılır
            Paragraph personalInfo = new Paragraph()  // Yeni bir paragraf oluşturuluyor
                    .add("Ad Soyad: Deniz Kaya\n")  // Metin ekleniyor (satır sonu ile)
                    .add("Adres: A Mah. A cad. A sok. no:12/1\n")
                    .add("Telefon: +90 000 000 0000\n")
                    .add("E-posta: deniza123@gmail.com\n\n")
                    .setTextAlignment(TextAlignment.LEFT);  // Metin hizası sol ayarlanıyor (resimin yanı)
            personalInfo.setFixedPosition(150, 700, 400);  // Paragraf sayfada sağ tarafa yerleştiriliyor
            document.add(personalInfo);  // Paragraf PDF'e ekleniyor

            // Eğitim Bilgileri
            document.add(new Paragraph("\n\n\n\n\n\n\n\n"));  // Birkaç satır boşluk ekleniyor (yukarıdaki metin ve aşağıdaki metin arasında boşluk bırakmak için)
            document.add(new Paragraph("EGITIM").setBold().setFontSize(14).setTextAlignment(TextAlignment.LEFT));  // Eğitim bağlığı kalın fontta yazdırılır ve bilgiler girilir
            document.add(new Paragraph("Bilgisayar Mühendisligi, ABC Universitesi (2015 - 2020)")
                    .setTextAlignment(TextAlignment.LEFT));  // Eğitim bilgisi ekleniyor
            document.add(new Paragraph("Yüksek Lisans, Yazilim Mühendisligi, XYZ Universitesi (2021 - 2023)")
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("\n"));

            // Sertifika Bilgisi
            document.add(new Paragraph("Sertifikalar").setBold().setFontSize(14).setTextAlignment(TextAlignment.LEFT));  // Sertifikalar başlığı kalın fontta yazdırılıyor
            document.add(new Paragraph("1. Java Geliştirici Sertifikasi - ABC Kurumu")
                    .setTextAlignment(TextAlignment.LEFT));  // Sertifika bilgisi ekleniyor
            document.add(new Paragraph("2. Veri Bilimi Sertifikasi - ABC Kurumu")
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("3. Proje Yönetimi Sertifikasi - ABC Kurumu")
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("\n"));

            // İş Deneyimi bilgisi
            document.add(new Paragraph("Deneyimler").setBold().setFontSize(14).setTextAlignment(TextAlignment.LEFT));  // Deneyimler başlığı kalın fontta yazdırılıyor
            document.add(new Paragraph("1. Yazilim Mühendisi, XYZ Sirketi (2020 - 2023)")
                    .setTextAlignment(TextAlignment.LEFT));  // İlk iş deneyimi ekleniyor
            document.add(new Paragraph("2. Stajyer Yazilim Gelistirici, ABC Sirketi (2019 - 2020)")
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("3. Proje Asistani, E Sirketi (2018 - 2019)")
                    .setTextAlignment(TextAlignment.LEFT));

            // PDF belgesi kapatılıyor
            document.close();
            System.out.println("PDF başarıyla oluşturuldu!");

            // Oluşturulan PDF dosyasını otomatik olarak açması için:
            File pdfFile = new File(pdfPath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (FileNotFoundException e) {  // Dosya bulunamazsa hata yakalanır
            e.printStackTrace();
        } catch (IOException e) {  // IO işlemlerinde bir hata olursa bu kısım çalışır
            e.printStackTrace();
        }
    }
}
