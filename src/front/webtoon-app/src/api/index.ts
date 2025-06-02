import axios from 'axios'

const apiClient = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
})

export default apiClient

export interface ICommonResponse<T = any> {
    code: number
    message: string
    data?: T
}