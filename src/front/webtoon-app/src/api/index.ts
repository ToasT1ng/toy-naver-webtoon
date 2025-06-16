import axios from 'axios'
import {useUserStore} from "@/stores/userStore";

const apiClient = axios.create({
    baseURL: process.env.VUE_APP_API_BASE_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
})

//TODO 토큰 refresh 로직 추가 필요
apiClient.interceptors.request.use(
    (config) => {
        const userStore = useUserStore()

        if (userStore.accessToken) {
            config.headers.Authorization = `Bearer ${userStore.accessToken}`
        }

        return config
    },
    (error) => Promise.reject(error)
)

export default apiClient

export interface ICommonResponse<T = any> {
    code: number
    message: string
    data?: T
}