const PATIENT_API = "/api/patients";

async function getPatients() {
    const response = await fetch(PATIENT_API);
    return response.json();
}

export { getPatients };