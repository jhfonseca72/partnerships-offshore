import axios from 'axios';
const API = 'http://localhost:8080/events';

class EventService {
    async getAllEvents() {
        const allEvents = await axios.get(API);        
        return allEvents.data;
    };

    async createEvent(event) {
        const eventCreated = await axios.post(API, event);
        return eventCreated;
    }
}

export default new EventService();