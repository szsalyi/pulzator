export class AuthRequest {
  loginIdentifier: string;
  password: string;

  constructor(username: string, password: string) {
    this.loginIdentifier = username;
    this.password = password;
  }
}
