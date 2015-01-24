package inventory

class Authority {

	String name

	static constraints = {
		name blank: false, unique: true
	}
}
