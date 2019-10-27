export class User {
  username: string
  firstname: string;
  lastname: string;
  password: string;
  email: string;
  enabled: boolean;
  roles: string[];

  constructor(username: string, firstname: string, lastname: string, passsword: string, email: string, enabled?: boolean, roles?: string[]) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.password = passsword;
    this.email = email;
    this.enabled = enabled;
    this.roles = roles;
  }
}
