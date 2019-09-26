package com.github.szsalyi.pulzator.users;

import com.github.szsalyi.pulzator.common.audit.DateAudit;
import com.github.szsalyi.pulzator.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USER")
public class User extends DateAudit {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Size(min = 4, max = 40)
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "FIRSTNAME", nullable = false, length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false, length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @NotNull
    @Size(min = 4, max = 100)
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @NaturalId
    @NotNull
    @Size(min = 4, max = 40)
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ENABLED", nullable = false)
    @NotNull
    private boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", insertable = false))
    private Set<Role> role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;
}
