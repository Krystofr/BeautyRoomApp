package app.christopher.beautyroomapp.model

class Specialists {

    private var image = 0
    private var name: String? = null
    private var phone: String? = null

    constructor()

    constructor(image: Int, name: String?, phone: String?) {
        this.image = image
        this.name = name
        this.phone = phone
    }

    fun getImage(): Int {
        return image
    }

    fun setImage(image: Int) {
        this.image = image
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPhone(): String? {
        return phone
    }
    fun setPhone(phone: String?) {
        this.phone = phone
    }

}