import axios from "axios";


// eslint-disable-next-line no-undef
export let backendUrl = import.meta.env.VITE_API_URL;
export const axiosApi = axios.create({
     baseURL: backendUrl,
    headers: {'Content-Type': 'application/json'},

})

