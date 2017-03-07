package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Table;
import org.mindrot.jbcrypt.BCrypt;

/**
 * User class
 */
@Entity
@Table(appliesTo="users")
public class User implements Serializable {

	private static final long serialVersionUID = 5316527283073594682L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String alias;
	private String name;
	private String email;
	private String passwdHash;

	/**
	 * User constructor.
	 * @param alias login or username, who must be unique
	 * @param name label
	 * @param email who must be unique
	 * @param password non crypted
	 */
	public User(String alias,
			String name,
			String email,
			String password)
	{
		this.alias = alias;
		this.name = name;
		this.email = email;
		setPassword(password);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Set a new passwordHash using
	 * <a href="https://en.wikipedia.org/wiki/Bcrypt">bCrypt algorithm</a>.
	 * Only hashed password will be save and no salt,
	 * to protect against rainbow attacks.
	 * @param password non crypted
	 */
	public void setPassword(String password) {
		this.passwdHash = BCrypt.hashpw(password, generateSalt());
	}

	/**
	 * Testing if password match hashed password using bCrypt algorithm.
	 * @param password to test
	 * @return true if password match
	 */
	public boolean isGoodPassword(String password) {
		return BCrypt.checkpw(password, this.passwdHash);
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	/**
	 * GenerateSalt log_rounds parameter determines the complexity
	 * the work factor is 2**log_rounds, and the default is 12.
	 * Always remember in computer science, is only a pseudo-random number.
	 */
	private String generateSalt() {
		return BCrypt.gensalt(12); // increase default values, who is 10.
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		return 	id == user.getId() &&
				alias.equals(user.getAlias()) &&
				name.equals(user.getName()) &&
				email.equals(user.getEmail()) &&
				passwdHash.equals(user.getPasswdHash());
	}

	@Override
	public String toString() {
		return "<" + id + "," + alias + "," + email + ">";
	}

}