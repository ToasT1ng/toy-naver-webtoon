import {ICommonResponse} from "@/api";
import {ApiError} from "@/errors/ApiError";

export function extractValidData<T>(response: ICommonResponse<T>): T {
    checkResponseCode(response)
    if (!response.data) {
        throw new ApiError('No data found in response', response.code)
    }
    return response.data
}

export function checkResponseCode<T>(response: ICommonResponse<T>) {
    if (response.code < 20000 || response.code >= 22700) {
        throw new ApiError(`Invalid response code: ${response.code}`, response.code)
    }
}