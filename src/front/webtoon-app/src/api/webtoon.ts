import apiClient from './index'
import {AxiosResponse} from "axios";

interface IDailyWebtoonsRequest {
    dayOfWeek: string
}

interface IDailyWebtoonsResponse {
    result: IDailyWebtoonResponse[]
}

interface IDailyWebtoonResponse {
    id: number
    title: string
    authorNames: string,
    rating: string,
}

export const getDailyWebtoon = async (
    req: IDailyWebtoonsRequest
): Promise<IDailyWebtoonsResponse> => {
    const response: AxiosResponse<IDailyWebtoonsResponse> = await apiClient.get<IDailyWebtoonsResponse>('/webtoons/daily', {
        params: req,
    })
    return response.data
}