package apitest.model;


/**
 * 
 * a 
 * 
 * 表名 assertinfo
 * mvn meizu:coper:lift -Dtable=campaign -Dtype=all -U
 * 
 * @since 1.0
 */
public class Assertinfo {

	/**
	*  id
	*/
	private int id; 
	/**
	*  caseid
	*/
	private int caseid; 
	/**
	* assert_type
	*/
	private String assert_type;

	public String getValue_class() {
		return value_class;
	}

	public void setValue_class(String value_class) {
		this.value_class = value_class;
	}

	private String value_class;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCaseid() {
		return caseid;
	}

	public void setCaseid(int caseid) {
		this.caseid = caseid;
	}

	public String getAssert_type() {
		return assert_type;
	}

	public void setAssert_type(String assert_type) {
		this.assert_type = assert_type;
	}

	public String getAssert_action() {
		return assert_action;
	}

	public void setAssert_action(String assert_action) {
		this.assert_action = assert_action;
	}

	public String getAssert_value() {
		return assert_value;
	}

	public void setAssert_value(String assert_value) {
		this.assert_value = assert_value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJsonpath() {
		return jsonpath;
	}

	public void setJsonpath(String jsonpath) {
		this.jsonpath = jsonpath;
	}

	/**
	*  assert_action
	*/
	private String assert_action;
	/**
	*  assert_value
	*/
	private String assert_value;
	/**
	*  note
	*/
	private String note; 
	/**
	*  status
	*/
	private String status;


	private String jsonpath;

}
