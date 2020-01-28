package board.board.dto;

/*
 * member table�����ê�Java code����������DTO�Ϋ��髹
 * 
 * @author Eonsu Bae(2020-01-28) 
 * @version 1.0
 */

public class MemberDto {

	private String id;
	private String password;
	private String name;
	private Role role;
	private boolean enabled;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
