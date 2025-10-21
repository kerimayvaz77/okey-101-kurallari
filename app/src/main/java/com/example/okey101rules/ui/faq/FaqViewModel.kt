package com.example.okey101rules.ui.faq

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.okey101rules.data.Faq
import javax.inject.Inject

class FaqViewModel @Inject constructor() : ViewModel() {

    private val _faqs = MutableLiveData<List<Faq>>()
    val faqs: LiveData<List<Faq>> = _faqs

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadFaqs() {
        _isLoading.value = true
        try {
            // Mock data - gerçek uygulamada JSON'dan yüklenecek
            val mockFaqs = listOf(
                Faq(1, "Gösterge var mı?", "• Hayır, 101 oyununda göstergenin herhangi bir fonksiyonu yoktur.\n• Gösterge sadece okey belirlemek için kullanılır.\n• Oyun sırasında gösterge ile ilgili özel kurallar yoktur.\n• Gösterge sadece okey taşını belirlemek için açılır."),
                Faq(2, "Kaç çift açmam lazım?", "• 101 oyununda minimum 5 çift gerekir.\n• Çiftler aynı renk ve sayıda olmalıdır.\n• Çiftler farklı renklerde olabilir.\n• Çiftler oyunu bitirmek için zorunludur."),
                Faq(3, "12-13-1 oluyor mu?", "• Hayır, olmaz. 101 oyununda geçerli değildir.\n• Sadece art arda gelen sayılar geçerlidir.\n• 1-2-3 veya 11-12-13 gibi sıralı diziler olur.\n• 12-13-1 gibi döngüsel diziler geçersizdir."),
                Faq(4, "Okey nasıl kullanılır?", "• Okey herhangi bir taşın yerine geçebilir.\n• Gerçek okey ve sahte okey vardır.\n• Okey ile per yapılabilir.\n• Okey ile çift yapılabilir."),
                Faq(5, "Per nasıl yapılır?", "• 3 veya daha fazla art arda gelen taşlar.\n• Aynı renkte olmalıdır.\n• En az 3 taş gerekir.\n• Per ile oyunu bitirebilirsiniz."),
                Faq(6, "Taş sayısı kaç?", "• Toplam 106 taş vardır.\n• 4 farklı renkte 1-13 arası.\n• Her sayıdan her renkte 2 adet.\n• 2 adet sahte okey taşı."),
                Faq(7, "Puanlama nasıl?", "• Açık taşların toplamı puanınızdır.\n• En az puan kazanır.\n• Ceza puanları vardır.\n• Bonus puanlar kazanılabilir."),
                Faq(8, "Kurallar nerede?", "• Uygulamanın kurallar sekmesinde.\n• Detaylı açıklamalar mevcuttur.\n• Kategori bazında düzenlenmiştir.\n• Arama yapabilirsiniz.")
            )
            _faqs.value = mockFaqs
        } catch (e: Exception) {
            _faqs.value = emptyList()
        } finally {
            _isLoading.value = false
        }
    }
}
