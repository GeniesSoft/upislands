import ReactPlayer from 'react-player'

const PostGallery = (props) => {

    const gallery = props.gallery;
    const videoGallery = props.videoGallery;

    if (videoGallery && videoGallery.length > 0) {
        switch (gallery.length) {
            case 0:
            return (
                <video className={"full"} controls >
                    <source src={videoGallery[0].url} type="video/mp4"/>
                </video>
                )

            case 1:
                return (
                    <div>
                        <video className={"half1"} controls >
                            <source src={videoGallery[0].url} type="video/mp4"/>
                        </video>
                        <img
                            className="half2"
                            src={gallery[0].url}
                            alt="image"
                        />
                    </div>
                );
            case 2:
                return (
                    <div>
                        <video className={"half1"} controls >
                            <source src={videoGallery[0].url} type="video/mp4"/>
                        </video>
                        <img
                            className="merge-quarter1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="merge-quarter2"
                            src={gallery[1].url}
                            alt="image"
                        />
                    </div>
                )
            case 3:
                return (
                    <div>
                        <ReactPlayer playing={true} controls={true} className={"full"} url={videoGallery[0].url} />
                        <img
                            className="merge-quarter1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="quarter3"
                            src={gallery[1].url}
                            alt="image"
                        />
                        <img
                            className="quarter4"
                            src={gallery[2].url}
                            alt="image"
                        />
                    </div>
                )
            default:
                return (
                    <div>
                        <ReactPlayer url={videoGallery[0].url} />
                        <img
                            className="quarter1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="quarter2"
                            src={gallery[1].url}
                            alt="image"
                        />
                        <img
                            className="quarter3"
                            src={gallery[2].url}
                            alt="image"
                        />
                        <img
                            className="quarter4"
                            src={gallery[3].url}
                            alt="image"
                        />
                    </div>
                )
        }
    } else {
        switch (gallery.length) {
            case 0:
                return (
                        <div>
                            <img
                                className="full"
                                src={"/images/coming-soon.png"}
                                alt="image"
                            />
                        </div>
                    );
            case 1:
                return (
                    <div>
                        <img
                            className="full"
                            src={gallery[0].url}
                            alt="image"
                        />
                    </div>
                );
            case 2:
                return (
                    <div>
                        <img
                            className="half1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="half2"
                            src={gallery[1].url}
                            alt="image"
                        />
                    </div>
                )
            case 3:
                return (
                    <div>
                        <img
                            className="half1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="merge-quarter1"
                            src={gallery[1].url}
                            alt="image"
                        />
                        <img
                            className="merge-quarter2"
                            src={gallery[2].url}
                            alt="image"
                        />
                    </div>
                )
            case 4:
                return (
                    <div>
                        <img
                            className="half1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="merge-quarter1"
                            src={gallery[1].url}
                            alt="image"
                        />
                        <img
                            className="quarter3"
                            src={gallery[2].url}
                            alt="image"
                        />
                        <img
                            className="quarter4"
                            src={gallery[3].url}
                            alt="image"
                        />
                    </div>
                )
            default:
                return (
                    <div>
                        <img
                            className="half1"
                            src={gallery[0].url}
                            alt="image"
                        />
                        <img
                            className="quarter1"
                            src={gallery[1].url}
                            alt="image"
                        />
                        <img
                            className="quarter2"
                            src={gallery[2].url}
                            alt="image"
                        />
                        <img
                            className="quarter3"
                            src={gallery[3].url}
                            alt="image"
                        />
                        <img
                            className="quarter4"
                            src={gallery[4].url}
                            alt="image"
                        />
                    </div>
                )
        }
    }



}

export default PostGallery;