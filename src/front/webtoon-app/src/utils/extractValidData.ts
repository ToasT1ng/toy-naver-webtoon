import {ICommonResponse} from "@/api";

export function extractValidData<T>(response: ICommonResponse<T>): T {
    if (!response.data) {
        throw new ApiError('No data found in response', response.code)
    }

    if (response.code < 20000 || response.code >= 22700) {
        throw new ApiError(`Invalid response code: ${response.code}`, response.code)
    }

    return response.data
}