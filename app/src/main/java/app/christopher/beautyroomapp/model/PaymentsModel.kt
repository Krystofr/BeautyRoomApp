package app.christopher.beautyroomapp.model

class PaymentsModel {
    var service_name: String? = null
    var service_time: String? = null
    var service_duration: String? = null
    var service_price: String? = null

    constructor(

        service_name: String?,
        service_time: String?,
        service_duration: String?,
        service_price: String?,

    ) {

        this.service_name = service_name
        this.service_time = service_time
        this.service_duration = service_duration
        this.service_price = service_price
    }

    constructor()

    fun getServiceName(): String? {
        return service_name
    }

    fun setServiceName(service_name: String?) {
        this.service_name = service_name
    }

    fun getServiceTime(): String? {
        return service_time
    }

    fun setServiceTime(service_time: String?) {
        this.service_time = service_time
    }

    fun getServiceDuration(): String? {
        return service_duration
    }

    fun setServiceDuration(service_duration: String?) {
        this.service_duration = service_duration
    }

    fun getServicePrice(): String? {
        return service_price
    }

    fun setServicePrice(service_price: String?) {
        this.service_price = service_price
    }
}
/*
    fun getCardHolderName(): String? {
        return card_holder_name
    }
    fun setCardHolderName(card_holder_name: String?) {
        this.card_holder_name = card_holder_name
    }

    fun getCardNumber(): String? {
        return card_number
    }
    fun setCardNumber(card_number: String?) {
        this.card_number = card_number
    }

    fun getExpDate(): String? {
        return exp_date
    }
    fun setExpDate(exp_date: String?) {
        this.exp_date = exp_date
    }
    fun getCVV(): String? {
        return cvv
    }
    fun setCVV(cvv: String?) {
        this.cvv = cvv
    }

    fun getDeleteBtn(): Int {
        return delete_btn
    }
    fun setDeleteBtn(delete_btn: Int) {
        this.delete_btn = delete_btn
    }
    */
