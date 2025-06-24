import {useMutation} from "@tanstack/vue-query";
import {extractValidData} from "@/utils/extractValidData";
import {loginCall, logoutCall} from "@/api/login";

interface ILoginRequest {
    username: string;
    password: string;
}

export const useLogin = () => {
    return useMutation({
        mutationFn: async (req: ILoginRequest) => {
            const result = await loginCall(req)
            return extractValidData(result)
        },
        retry: 0,
    })
}

interface ILogoutRequest {
    accessToken: string
}

export const useLogout = () => {
    return useMutation({
        mutationFn: async (req: ILogoutRequest) => {
            const result = await logoutCall(req)
            return extractValidData(result)
        },
        retry: 0,
    })
}