function createDoctorCard(doctor) {
    return `
        <div class="doctor-card">
            <h3>${doctor.name}</h3>
            <p>${doctor.specialty}</p>
        </div>
    `;
}