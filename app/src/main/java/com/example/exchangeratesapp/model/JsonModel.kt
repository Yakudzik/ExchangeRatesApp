package com.example.exchangeratesapp.model


import com.google.gson.annotations.SerializedName

data class JsonModel(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: Valute
) {
    data class Valute(
        @SerializedName("AUD")
        val aUD: myValGeneralValute,
        @SerializedName("AZN")
        val aZN: myValGeneralValute,
        @SerializedName("GBP")
        val gBP: myValGeneralValute,
        @SerializedName("AMD")
        val aMD: myValGeneralValute,
        @SerializedName("BYN")
        val bYN: myValGeneralValute,
//        @SerializedName("BGN")
//        val bGN: BGN,
//        @SerializedName("BRL")
//        val bRL: BRL,
//        @SerializedName("HUF")
//        val hUF: HUF,
//        @SerializedName("HKD")
//        val hKD: HKD,
//        @SerializedName("DKK")
//        val dKK: DKK,
//        @SerializedName("USD")
//        val uSD: USD,
//        @SerializedName("EUR")
//        val eUR: EUR,
//        @SerializedName("INR")
//        val iNR: INR,
//        @SerializedName("KZT")
//        val kZT: KZT,
//        @SerializedName("CAD")
//        val cAD: CAD,
//        @SerializedName("KGS")
//        val kGS: KGS,
//        @SerializedName("CNY")
//        val cNY: CNY,
//        @SerializedName("MDL")
//        val mDL: MDL,
//        @SerializedName("NOK")
//        val nOK: NOK,
//        @SerializedName("PLN")
//        val pLN: PLN,
//        @SerializedName("RON")
//        val rON: RON,
//        @SerializedName("XDR")
//        val xDR: XDR,
//        @SerializedName("SGD")
//        val sGD: SGD,
//        @SerializedName("TJS")
//        val tJS: TJS,
//        @SerializedName("TRY")
//        val tRY: TRY,
//        @SerializedName("TMT")
//        val tMT: TMT,
//        @SerializedName("UZS")
//        val uZS: UZS,
//        @SerializedName("UAH")
//        val uAH: UAH,
//        @SerializedName("CZK")
//        val cZK: CZK,
//        @SerializedName("SEK")
//        val sEK: SEK,
//        @SerializedName("CHF")
//        val cHF: CHF,
//        @SerializedName("ZAR")
//        val zAR: ZAR,
//        @SerializedName("KRW")
//        val kRW: KRW,
//        @SerializedName("JPY")
//        val jPY: JPY
    ) {
        data class myValGeneralValute(
            @SerializedName("NumCode")
            var numCode: String,
            @SerializedName("CharCode")
            var charCode: String,
            @SerializedName("Nominal")
            var nominal: Int,
            @SerializedName("Name")
            var name: String,
            @SerializedName("Value")
            var value: String,
            @SerializedName("Previous")
            var previous: String
        )

        data class mySimpleValute(
            var charCode: String,
            var name: String,
            var originalValue: String,
            var newlValue: String
        )
    }
}