const API_URL = "/api/doctors";

async function getDoctors() {
    const response = await fetch(API_URL);
    return response.json();
}

async function createDoctor(doctor) {
    const response = await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(doctor)
    });

    return response.json();
}

export { getDoctors, createDoctor };