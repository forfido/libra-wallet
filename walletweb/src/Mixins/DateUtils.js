export const DateControl = {
  data() {
    return {};
  },

  methods: {
    getDateAndTime(date) {
      if (date !== null) {
        let hour = date.getHours();
        let minutes = date.getMinutes();
        let FullDate =
          date.getFullYear() + "/" + date.getMonth() + 1 + "/" + date.getDate();

        return FullDate + " / " + hour + " : " + minutes;
      } else {
        return null;
      }
    }
  }
};
