import apiClient, {ICommonResponse} from "@/api/index";

interface ILoginRequest {
    username: string
    password: string
}

interface ILoginResponse {
    accessToken: string
    refreshToken: string
    userId: number
    username: string
    nickname?: string
    profileImage?: string
}


export const loginCall = async (
    req: ILoginRequest
): Promise<ICommonResponse<ILoginResponse>> => {
    const response = await apiClient.post<ICommonResponse<ILoginResponse>>('/auth/login', req)
    return response.data
}

interface ILogoutRequest {
    accessToken: string
}

export const logoutCall = async (
    req: ILogoutRequest
): Promise<ICommonResponse> => {
    const response = await apiClient.post<ICommonResponse<ILoginResponse>>('/auth/login', req)
    return response.data
}