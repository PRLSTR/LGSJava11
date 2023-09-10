package ja.domain;
import java.time.LocalDate;

public class Subscribe {
	private int id;
	private int userID;
	private int magazineID;
	private boolean subStatus;
	private LocalDate subDate;
	private int subPeriod;

	public Subscribe(int id, int userID, int magazineID, boolean subStatus, LocalDate subDate, int subPeriod) {
		this.id = id;
		this.userID = userID;
		this.magazineID = magazineID;
		this.subStatus = subStatus;
		this.subDate = subDate;
		this.subPeriod = subPeriod;
	}

	public Subscribe(int userID, int magazineID, boolean subStatus, LocalDate subDate, int subPeriod) {
		this.userID = userID;
		this.magazineID = magazineID;
		this.subStatus = subStatus;
		this.subDate = subDate;
		this.subPeriod = subPeriod;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMagazineID() {
		return magazineID;
	}

	public void setMagazineID(int magazineID) {
		this.magazineID = magazineID;
	}

	public boolean getSubscribeStatus() {
		return subStatus;
	}

	public void setSubStatus(boolean subStatus) {
		this.subscribeStatus = subStatus;
	}

	public LocalDate getSubDate() {
		return subDate;
	}

	public void setSubDate(LocalDate subDate) {
		this.subDate = subDate;
	}

	public int getSubPeriod() {
		return subPeriod;
	}

	public void setSubPeriod(int subPeriod) {
		this.subPeriod = subPeriod;
	}

	@Override
	public String toString() {
		if (id == 0)
			return "userID#" + userID + ", magazineID#" + magazineID + ", subscribe status: " + subStatus + ", subscribe date: " + subDate + ", period: " + subPeriod + " months";
		else
			return "Subscribe ID#" + id + ": userID#" + userID + ", magazineID#" + magazineID + ", subscribe status: " + subStatus + ", subscribe date: " + subDate + ", period: " + subPeriod + " months";
	}
}