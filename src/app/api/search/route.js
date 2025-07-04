export async function GET(request) {
    const { searchParams } = new URL(request.url);
    const keyword = searchParams.get('keyword');
    
    const res = await fetch(`http://158.179.216.164:8080/api/clients/search?keyword=${keyword}`);
    const data = await res.json();
    return Response.json(data);
}