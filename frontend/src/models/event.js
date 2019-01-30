class Event {
    constructor(id, name, date, venue) {
        this.id = id || '';
        this.name = name || '';
        this.date = date || '';
        this.venue = venue || '';
    }
}

export default Event;