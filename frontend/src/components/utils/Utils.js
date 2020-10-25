import MarkdownIt from "markdown-it";

export const generateId = () => ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g,
    c => (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16));

export const htmlRender = (text, data) => {
    const mdParser = new MarkdownIt();
    if (!data)
        return mdParser.render(text);

    const codeQuote = "```";
    let templateArr = text.match(/\${.+?}/g) || [];
    templateArr.forEach(function (item) {
        const replaceObject = data.find(dataItem => dataItem.name === item.substring(2, item.length - 1));
        const replacer = replaceObject ? replaceObject.description : "UNKNOWN!!!";
        text = text.replace(item, replacer.length === 0 ? "" : `${codeQuote}${replacer}${codeQuote}`);
    });
    return mdParser.render(text);
};