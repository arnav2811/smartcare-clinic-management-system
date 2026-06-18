function login(token, role) {
    localStorage.setItem("jwt", token);
    localStorage.setItem("role", role);
}

function logout() {
    localStorage.removeItem("jwt");
    localStorage.removeItem("role");
}