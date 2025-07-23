import {useMutation} from "@tanstack/vue-query";
import {extractValidData} from "@/utils/extractValidData";
import {loginCall, logoutCall} from "@/api/login";
import {AxiosError} from "axios";
import {ApiError} from "@/errors/ApiError";
import {ICommonResponse} from "@/api";

interface ILoginRequest {
    username: string;
    password: string;
}

export const useLogin = () => {
    return useMutation({
        mutationFn: async (req: ILoginRequest) => {
            //TODO 오류 Converting 하는 부분은 모든 API에 필요함
            try {
                const result = await loginCall(req)
                return extractValidData(result)
            } catch (err) {
                if (err instanceof AxiosError) {
                    const response = err.response?.data as ICommonResponse
                    throw new ApiError(response.message, response.code)
                }
                throw err
            }
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