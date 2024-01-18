import { reactive } from "vue";

export const store = reactive({
    pathApiUsers: "http://localhost:8080/api/v1/users",
    arrayUsers: [],
    loading: true,
});