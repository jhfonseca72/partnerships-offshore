import axios from 'axios';
const API = 'http://localhost:8080/events';

class EventService {
    async getAllEvents() {
        try {
            const allEvents = await axios.get(API);
            return allEvents.data;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    };

    async createEvent(event) {
        try {
            const eventCreated = await axios.post(API, event);
            return eventCreated;
        } catch (error) {
            throw new Error(error.response.data.message);
        }
    }
}

export default new EventService();