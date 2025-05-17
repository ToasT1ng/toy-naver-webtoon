import apiClient from './index'
import {AxiosResponse} from "axios";

interface IDailyRecommendedWebtoonsResponse {
    result: IDailyRecommendedWebtoonResponse[]
}

interface IDailyRecommendedWebtoonResponse {
    id: number
    title: string
    thumbnail: string
    authorNames: string,
    latestEpisodeName: string,
    rating: string,
}

export const getDailyRecommendedWebtoon = async (
    req: IDailyWebtoonsRequest
): Promise<IDailyRecommendedWebtoonsResponse> => {
    const response: AxiosResponse<IDailyRecommendedWebtoonsResponse> = await apiClient.get<IDailyRecommendedWebtoonsResponse>('/webtoons/daily/recommend/three', {
        params: req,
    })
    return response.data
}

interface IDailyWebtoonsRequest {
    dayOfWeek: string
}

interface IDailyWebtoonsResponse {
    result: IDailyWebtoonResponse[]
}

interface IDailyWebtoonResponse {
    id: number
    title: string
    thumbnail: string
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

interface IMonthlyRecommendedWebtoonsResponse {
    result: IMonthlyRecommendedWebtoonResponse[]
}

interface IMonthlyRecommendedWebtoonResponse {
    id: number
    title: string
    thumbnail: string
    authorNames: string,
    description: string,
}

export const getMonthlyRecommendedWebtoon = async (): Promise<IMonthlyRecommendedWebtoonsResponse> => {
    const response: AxiosResponse<IMonthlyRecommendedWebtoonsResponse> = await apiClient.get<IMonthlyRecommendedWebtoonsResponse>('/webtoons/monthly/recommend/three',)
    return response.data
}

interface IEveryWebtoonResponse {
    result: Record<string, IEachWebtoonResponse[]>;
}

interface IEachWebtoonResponse {
    id: number
    title: string
    thumbnail: string
}

export const getEveryWebtoon = async (): Promise<IEveryWebtoonResponse> => {
    const response: AxiosResponse<IEveryWebtoonResponse> = await apiClient.get<IEveryWebtoonResponse>('/webtoons',)
    return response.data
}