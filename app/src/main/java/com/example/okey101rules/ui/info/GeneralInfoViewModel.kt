package com.example.okey101rules.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.okey101rules.data.InfoCard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeneralInfoViewModel @Inject constructor() : ViewModel() {

    private val _infoCards = MutableLiveData<List<InfoCard>>()
    val infoCards: LiveData<List<InfoCard>> = _infoCards

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadInfoCards() {
        _isLoading.value = true
        try {
            // Burada assets'ten JSON okunacak
            // Şimdilik mock data kullanıyoruz
            val mockCards = listOf(
                InfoCard("Oyun Ekipmanı", "• 106 taş\n• 4 farklı renk\n• Istaka (taş tutucu)\n• Puan cetveli", "ic_game_equipment"),
                InfoCard("Oyuncu Sayısı", "• 4 oyuncu ile oynanır\n• Saat yönünün tersine sıra\n• Her oyuncuya 21 taş\n• İlk oyuncuya 22 taş", "ic_players"),
                InfoCard("Taş Renkleri", "• Kırmızı 🔴\n• Mavi 🔵\n• Sarı 🟡\n• Siyah ⚫", "ic_colors"),
                InfoCard("Okey Sistemi", "• Gerçek okey taşları\n• Sahte okey taşları\n• Gösterge belirleme\n• Okey değiştirme", "ic_joker"),
                InfoCard("Oyun Süresi", "• 3-11 tur arası\n• Ortalama 30-60 dakika\n• Tur sayısı önceden belirlenir\n• En az puan kazanır", "ic_timer"),
                InfoCard("Puanlama", "• Açık taşların toplamı\n• En az puan kazanır\n• Ceza puanları\n• Bonus puanlar", "ic_scoring"),
                InfoCard("Kurallar", "• El açma kuralları\n• Per yapma kuralları\n• Çift kuralları\n• Özel durumlar", "ic_rules"),
                InfoCard("Strateji", "• Taş saklama\n• Per yapma zamanı\n• Risk yönetimi\n• Rakip analizi", "ic_strategy"),
                InfoCard("İpuçları", "• Taşları organize edin\n• Per fırsatlarını kaçırmayın\n• Rakibinizi gözlemleyin\n• Sabırlı olun", "ic_tips"),
                InfoCard("Tarihçe", "• Türkiye'de popüler\n• Rummy oyunundan türedi\n• 20. yüzyılda yaygınlaştı\n• Aile oyunu geleneği", "ic_history")
            )
            _infoCards.value = mockCards
        } catch (e: Exception) {
            _infoCards.value = emptyList()
        } finally {
            _isLoading.value = false
        }
    }
}
