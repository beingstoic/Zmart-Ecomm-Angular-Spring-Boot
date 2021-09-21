export interface LoginResponse{
    userId: String;
    username: String;
    fullname: String;
    email: String;
    role: String;
}
export interface LoginRequest{
    username: String;
    password: String;
}