const origin = "http://localhost:8080";

function parseJSON(response) {
    return response.text().then((text) =>
        (text && text.length)
            ? new Promise((resolve) =>
                resolve({
                    status: response.status,
                    ok: response.ok,
                    json: JSON.parse(text)
                }))
            : Promise.resolve({
                status: response.status,
                ok: response.ok
            })
    );
}

export default function request(url, options) {
    return new Promise((resolve, reject) => {
        fetch(`${origin}${url}`, options)
            .then(parseJSON)
            .then((response) => {
                if (response.ok) {
                    return resolve(response);
                }
                return reject(response);
            })
    });
}