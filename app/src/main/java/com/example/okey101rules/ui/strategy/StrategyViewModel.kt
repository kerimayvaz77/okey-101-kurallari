package com.example.okey101rules.ui.strategy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.okey101rules.data.Strategy
import javax.inject.Inject

class StrategyViewModel @Inject constructor() : ViewModel() {

    private val _strategies = MutableLiveData<List<Strategy>>()
    val strategies: LiveData<List<Strategy>> = _strategies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadStrategies() {
        _isLoading.value = true
        try {
            // Mock data - gerçek uygulamada JSON'dan yüklenecek
            val mockStrategies = listOf(
                Strategy(1, "El Açma Stratejisi", "• El açmadan önce taşlarınızı kontrol edin\n• En az 5 çift olduğundan emin olun\n• Per yapma fırsatlarını değerlendirin\n• Risk almadan önce düşünün", "Genel"),
                Strategy(2, "Okey Kullanımı", "• Okey'i mümkün olduğunca geç kullanın\n• Okey çok değerli bir taştır\n• Gerçek okey ile sahte okey'i ayırt edin\n• Okey ile per yapma fırsatlarını kaçırmayın", "Okey"),
                Strategy(3, "Rakip Takibi", "• Rakip oyuncuların açtığı taşları takip edin\n• Hangi taşları istediğini analiz edin\n• Rakibin elini tahmin etmeye çalışın\n• Stratejinizi buna göre belirleyin", "Takip"),
                Strategy(4, "Per Yapma Teknikleri", "• 3 veya daha fazla art arda taşları gruplayın\n• Aynı renkte taşları bir arada tutun\n• Per yapma fırsatlarını kaçırmayın\n• Per ile oyunu hızla bitirin", "Per"),
                Strategy(5, "Çift Stratejisi", "• Minimum 5 çift gereklidir\n• Çiftleri farklı renklerde yapın\n• Çift yapma fırsatlarını değerlendirin\n• Çiftler oyunu bitirmek için zorunludur", "Çift"),
                Strategy(6, "Taş Saklama", "• Değerli taşları saklayın\n• Rakibin ihtiyacı olan taşları saklayın\n• Taşları organize edin\n• Gereksiz taşları erken atın", "Taş"),
                Strategy(7, "Puan Yönetimi", "• En az puan ile oyunu bitirin\n• Ceza puanlarından kaçının\n• Bonus puanları kazanmaya çalışın\n• Puan hesaplamasını öğrenin", "Puan"),
                Strategy(8, "Risk Analizi", "• Risk almadan önce düşünün\n• Başarı şansınızı değerlendirin\n• Kaybetme riskini hesaplayın\n• Güvenli hamleleri tercih edin", "Risk"),
                Strategy(9, "Zaman Yönetimi", "• Acele etmeyin, sabırlı olun\n• Doğru zamanı bekleyin\n• Per fırsatlarını kaçırmayın\n• Stratejik düşünün", "Zaman"),
                Strategy(10, "Dikkat ve Odaklanma", "• Oyuna tam odaklanın\n• Rakibin hamlelerini izleyin\n• Detayları kaçırmayın\n• Konsantrasyonunuzu koruyun", "Dikkat")
            )
            _strategies.value = mockStrategies
        } catch (e: Exception) {
            _strategies.value = emptyList()
        } finally {
            _isLoading.value = false
        }
    }
}
