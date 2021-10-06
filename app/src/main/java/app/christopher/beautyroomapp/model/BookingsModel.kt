package app.christopher.beautyroomapp.model

class BookingsModel {
    private var service: String? = null
    private var time: String? = null
    private var date: String? = null
    private var price: String? = null

    constructor(service: String?, time: String?, date: String?, price: String?) {
        this.service = service
        this.time = time
        this.date = date
        this.price = price
    }

    constructor()

    fun getService(): String? {
        return service
    }

    fun setServiceName(service: String?) {
        this.service = service
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getPrice(): String? {
        return price
    }

    fun setPrice(price: String?) {
        this.price = price
    }
}