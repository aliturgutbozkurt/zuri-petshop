import client from "./client.js";

const send = (message, listingId) =>
  client.post("/messages", {
    message,
    listingId,
  });

export default {
  send,
};
