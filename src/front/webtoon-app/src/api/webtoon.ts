import apiClient from './index'
import {AxiosResponse} from "axios";

interface IDailyWebtoonRequest {
    dayOfWeek: string
}

interface IDailyWebtoonResponse {
    result: {
        id: number
        title: string
        authorNames: string,
        rating: string,
    }
}

export const getDailyWebtoon = async (
    req: IDailyWebtoonRequest
): Promise<IDailyWebtoonResponse> => {
    const response: AxiosResponse<IDailyWebtoonResponse> = await apiClient.get<IDailyWebtoonResponse>('/webtoons/daily', {
        params: req,
    })
    return response.data
}